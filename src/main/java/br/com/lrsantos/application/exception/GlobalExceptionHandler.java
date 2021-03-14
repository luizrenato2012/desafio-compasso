package br.com.lrsantos.application.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<MensagemErro> mensagens = ex.getBindingResult().getAllErrors()
			.stream()
			.map(error -> new MensagemErro(error.getDefaultMessage()))
			.collect(Collectors.toList());
		return this.handleExceptionInternal(ex, mensagens, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	protected ResponseEntity<Object> handleObjetoNaoEncontradoException(ObjetoNaoEncontradoException ex, WebRequest request) {
		MensagemErro mensagem = new MensagemErro(ex.getMessage());
		return this.handleExceptionInternal(ex,mensagem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(RequestInvalidoException.class)
	protected ResponseEntity<Object> handleRequestInvalidoException(RequestInvalidoException ex, WebRequest request) {
		MensagemErro mensagem = new MensagemErro(ex.getMessage());
		return this.handleExceptionInternal(ex,mensagem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	

	
}

@Getter
@Setter
class MensagemErro {
	private LocalDateTime dataHora;
	private String descricaoErro;
	
	public MensagemErro(String description) {
		dataHora = LocalDateTime.now();
		this.descricaoErro=description;
	}
}

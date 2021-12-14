package br.com.dh.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.dh.model.Categoria;

@RestControllerAdvice
public class MyExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MyException.class)
	public MyExceptionErrorDto handle(MyException exception) {
		Categoria categoria = exception.getMovimentacao().getCategoria();
		MyExceptionErrorDto erro = new MyExceptionErrorDto(
			categoria.getNome(), 
			categoria.getLimiteMensal().toString(),
			exception.getMessage());
		return erro;
	}

}

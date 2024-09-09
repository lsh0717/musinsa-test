package org.lsh.brandproduct.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ExceptionRs {
	private String exceptionType;
	private String message;
}

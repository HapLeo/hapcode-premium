package top.hapleow.hapcodepremium.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务统一异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException {

    private String message;

}

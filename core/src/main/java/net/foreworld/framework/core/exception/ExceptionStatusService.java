/**
 */
package net.foreworld.framework.core.exception;

import net.foreworld.framework.core.logs.LogEnabled;

import org.restlet.data.CharacterSet;
import org.restlet.data.Language;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.Representation;
import org.restlet.resource.StringRepresentation;
import org.restlet.service.StatusService;

/**
 * <p>
 * Title: ErrorStatusService
 *
 * @author huangxin
 *         </p>
 */
public class ExceptionStatusService extends StatusService implements LogEnabled {

	@Override
	public Representation getRepresentation(Status status, Request request,
			Response response) {
		if (status != null) {
			Throwable throwable = status.getThrowable();

			if (throwable != null) {
				try {
					if (throwable instanceof SysException) { // 自定义异常
						String message = throwable.getMessage();
						log.error(message);
						return new StringRepresentation(message,
								MediaType.APPLICATION_JSON, Language.ALL,
								CharacterSet.UTF_8);
					} else { // 其他异常

						String defautMessage = status.getDescription();
						log.error(defautMessage);
						return new StringRepresentation(defautMessage,
								MediaType.APPLICATION_JSON, Language.ALL,
								CharacterSet.UTF_8);
					}
				} catch (Exception e) {
				}
			} else if (status.getCode() / 100 == 4) { // 大于400的异常
				String defautMessage = status.getDescription();
				log.error(defautMessage);
				return new StringRepresentation(defautMessage,
						MediaType.APPLICATION_JSON, Language.ALL,
						CharacterSet.UTF_8);
			}

		}
		return super.getRepresentation(status, request, response);

	}

}

package com.media.smartcore.validator;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.omnifaces.util.Components;

/**
* Validate password.
*
* @version 1.0.0
*/
@FacesValidator("form.PasswordValidator")
public class PasswordValidator implements Validator {
	private Pattern pattern;
	private Matcher matcher;

	// Chu hoa, chu thuong, so, ky tu dac biet, do dai toi thieu 8 ky tu.
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*)(]).{8,})";

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	/**
	 * Validate password with regular expression
	 * 
	 * @param password
	 *            password for validation
	 * @return true valid password, false invalid password
	 */
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msg");

		String message = ((String) (Components.getOptionalLabel(component) == null ? ""
				: Components.getOptionalLabel(component)).toString().trim() + ": ") + bundle.getString("validator.password");

		matcher = pattern.matcher(value.toString().trim());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					message, message);

			throw new ValidatorException(msg);
		}

	}

	/**
	 * Validate password with regular expression
	 * 
	 * @param password
	 *            password for validation
	 * @return true valid password, false invalid password
	 */
	public boolean check(final String password) {

		matcher = pattern.matcher(password);
		return matcher.matches();

	}
}
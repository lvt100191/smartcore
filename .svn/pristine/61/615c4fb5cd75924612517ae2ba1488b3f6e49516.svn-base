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
* Validate number.
*
*/
@FacesValidator("form.NumberValidator")
public class NumberValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String PHONE_PATTERN = "^[0-9]+$";

	public NumberValidator() {
		pattern = Pattern.compile(PHONE_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msg");

		String message = ((String) (Components.getOptionalLabel(component) == null ? ""
				: Components.getOptionalLabel(component)).toString().trim() + ": ")
				+ bundle.getString("validator.number");

		matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					message, message);

			throw new ValidatorException(msg);
		}
	}
}

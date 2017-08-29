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
* Validate special character.
*
* @version 1.0.0
*/
@FacesValidator("form.SpecialCharacterValidator")
public class SpecialCharacterValidator implements Validator {
	private Pattern pattern;
	private Matcher matcher;

//	private static final String SPECIAL_CHARACTER_PATTERN = ".*[,/'#~@\\x5B\\x5D}{+_)(*&^%$£\"!\\|<>]+.*";
	private static final String SPECIAL_CHARACTER_PATTERN = ".*['#~@\\x5B\\x5D}{+)(*&^%$£\"!\\|<>]+.*";

	public SpecialCharacterValidator() {
		pattern = Pattern.compile(SPECIAL_CHARACTER_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msg");

		String message = ((String) (Components.getOptionalLabel(component) == null ? ""
				: Components.getOptionalLabel(component)).toString().trim() + ": ")
				+ bundle.getString("validator.special");

		matcher = pattern.matcher(value.toString().trim());
		if (matcher.matches()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					message, message);

			throw new ValidatorException(msg);
		}
	}
}

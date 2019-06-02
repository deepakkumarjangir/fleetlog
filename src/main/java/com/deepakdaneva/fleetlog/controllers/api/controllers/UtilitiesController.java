/**
 * FleetLog
 * May 12, 2019 1:28:26 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.controllers;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.Random;

import org.passay.CharacterData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilities")
public class UtilitiesController {

	public static final Logger logger = LoggerFactory.getLogger(UtilitiesController.class);

	private Random random = new Random();

	@PostMapping(path = "/getRandomPassword")
	public String getRandomPassword() {

		PasswordGenerator passwordGenerator = new PasswordGenerator();

		CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase);
		lowerCaseRule.setNumberOfCharacters(1);

		CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase);
		upperCaseRule.setNumberOfCharacters(1);

		CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit);
		digitRule.setNumberOfCharacters(1);

		CharacterData selectedSpecialChars = new CharacterData() {
			public String getErrorCode() {
				return "0";
			}

			public String getCharacters() {
				return "!@#$%^&*()_+<>{}[]";
			}
		};

		CharacterRule specialCharRule = new CharacterRule(selectedSpecialChars);
		specialCharRule.setNumberOfCharacters(1);

		return passwordGenerator.generatePassword(this.random.nextInt(16 - 8) + 8, specialCharRule, lowerCaseRule, upperCaseRule, digitRule);
	}

}

package com.app.art.registry.model.converter.user;

import com.app.art.registry.model.user.UserName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserNameConverter implements AttributeConverter<UserName, String> {

    private final String SEPARATOR = ", ";


    public String convertToDatabaseColumn(UserName un) {
        return un.getFirstName() + " " + un.getLastName();
    }

    public UserName convertToEntityAttribute(String dbPersonName) {
        if (dbPersonName == null || dbPersonName.isEmpty()) {
            return null;
        }

        String[] pieces = dbPersonName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        UserName personName = new UserName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbPersonName.contains(SEPARATOR)) {
            personName.setFirstName(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                personName.setLastName(pieces[1]);
            }
        } else {
            personName.setLastName(firstPiece);
        }

        return personName;
    }
}

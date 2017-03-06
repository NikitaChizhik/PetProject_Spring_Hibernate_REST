package com.nikitachizhik91.university.daoImpl;

import java.sql.Timestamp;
import java.util.Date;

public class DateConverter {

	public static Timestamp toTimestamp(Date date) {
		Timestamp timestamp = new Timestamp(date.getTime());

		return timestamp;
	}

	public static Date toDate(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());

		return date;
	}
}

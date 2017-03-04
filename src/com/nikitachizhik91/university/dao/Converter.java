package com.nikitachizhik91.university.dao;

import java.sql.Timestamp;
import java.util.Date;

public class Converter {

	public static Timestamp convertUtilDateToTimestamp(Date date) {
		Timestamp timestamp = new Timestamp(date.getTime());

		return timestamp;
	}

	public static Date convertTimestampToUtilDate(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());

		return date;
	}
}

package com.k2h2.counam.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateSerializer extends JsonSerializer<Date> {
	public void serialize(Date src, JsonGenerator g, SerializerProvider p) throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh/mm/ss");
		String formattedDate = formatter.format(src);
		g.writeString(formattedDate);
	}
}

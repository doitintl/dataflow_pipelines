package utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GsonUTCDateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

  @Override
  public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
    return new JsonPrimitive(getNewDateFormat().format(date));
  }

  @Override
  public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
    try {
      return getNewDateFormat().parse(jsonElement.getAsString());
    } catch (ParseException e) {
      throw new JsonParseException(e);
    }
  }

  private DateFormat getNewDateFormat() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return dateFormat;
  }
}
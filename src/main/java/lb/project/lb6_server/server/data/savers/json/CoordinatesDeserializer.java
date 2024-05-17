package lb.project.lb6_server.server.data.savers.json;

import com.google.gson.*;
import lb.project.lb6_server.lib.entities.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    @Override
    public Coordinates deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        double x = jsonObject.get("x").getAsInt();
        Long y = jsonObject.get("y").getAsLong();
        try {
            return new Coordinates(x, y);
        }
        catch (Exception e) {
            System.out.println("Некорректные данные в сохранении. " + e.getMessage());
            System.exit(1);

        }
        return null;

    }
}

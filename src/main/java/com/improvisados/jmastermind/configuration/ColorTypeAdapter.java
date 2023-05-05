/*
 * Copyright (C) 2023 jomartinez
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.improvisados.jmastermind.configuration;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author jomartinez
 */
public class ColorTypeAdapter extends TypeAdapter<Color> {

    @Override
    public void write(JsonWriter writer, Color color) throws IOException {
        if (color != null) {
            writer.beginObject();
            writer.name("r");
            writer.value(color.getRed());
            writer.name("g");
            writer.value(color.getGreen());
            writer.name("b");
            writer.value(color.getBlue());
            writer.endObject();

        } else {
            writer.nullValue();
        }

    }

    @Override
    public Color read(JsonReader reader) throws IOException {
        int r = 0, g = 0, b = 0;
        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "r":
                    r = reader.nextInt();
                    break;
                case "g":
                    g = reader.nextInt();
                    break;
                case "b":
                    b = reader.nextInt();
                    break;
            }
           
        }
 reader.endObject();
        return new Color(r, g, b);
    }
}

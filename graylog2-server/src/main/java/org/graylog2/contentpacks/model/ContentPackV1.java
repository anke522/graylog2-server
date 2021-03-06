/**
 * This file is part of Graylog.
 *
 * Graylog is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog2.contentpacks.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableSet;
import org.bson.types.ObjectId;
import org.graylog2.contentpacks.model.constraints.Constraint;
import org.graylog2.contentpacks.model.entities.Entity;
import org.graylog2.contentpacks.model.parameters.Parameter;

import javax.annotation.Nullable;
import java.net.URI;

@AutoValue
@JsonAutoDetect
@JsonDeserialize(builder = AutoValue_ContentPackV1.Builder.class)
public abstract class ContentPackV1 implements ContentPack {
    static final String VERSION = "1";
    static final String FIELD_NAME = "name";
    static final String FIELD_SUMMARY = "summary";
    static final String FIELD_DESCRIPTION = "description";
    static final String FIELD_VENDOR = "vendor";
    static final String FIELD_URL = "url";
    static final String FIELD_REQUIRES = "requires";
    static final String FIELD_PARAMETERS = "parameters";
    static final String FIELD_ENTITIES = "entities";
    static final String FIELD_DB_ID = "_id";

    @Nullable
    @JsonView(ContentPackView.DBView.class)
    @JsonProperty(FIELD_DB_ID)
    public abstract ObjectId _id();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_NAME)
    public abstract String name();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_SUMMARY)
    public abstract String summary();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_DESCRIPTION)
    public abstract String description();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_VENDOR)
    public abstract String vendor();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_URL)
    public abstract URI url();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_REQUIRES)
    public abstract ImmutableSet<Constraint> requires();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_PARAMETERS)
    public abstract ImmutableSet<Parameter> parameters();

    @JsonView(ContentPackView.HttpView.class)
    @JsonProperty(FIELD_ENTITIES)
    public abstract ImmutableSet<Entity> entities();

    public static Builder builder() {
        return new AutoValue_ContentPackV1.Builder()
                .requires(ImmutableSet.of())
                .parameters(ImmutableSet.of());
    }

    @AutoValue.Builder
    public abstract static class Builder implements ContentPack.ContentPackBuilder<Builder> {

        @JsonProperty(FIELD_DB_ID)
        @JsonView(ContentPackView.DBView.class)
        public abstract Builder _id(ObjectId _id);

        @JsonProperty(FIELD_NAME)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder name(String name);

        @JsonProperty(FIELD_SUMMARY)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder summary(String summary);

        @JsonProperty(FIELD_DESCRIPTION)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder description(String description);

        @JsonProperty(FIELD_VENDOR)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder vendor(String vendor);

        @JsonProperty(FIELD_URL)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder url(URI url);

        @JsonProperty(FIELD_REQUIRES)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder requires(ImmutableSet<Constraint> requirements);

        @JsonProperty(FIELD_PARAMETERS)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder parameters(ImmutableSet<Parameter> parameters);

        @JsonProperty(FIELD_ENTITIES)
        @JsonView(ContentPackView.HttpView.class)
        public abstract Builder entities(ImmutableSet<Entity> entities);

        abstract ContentPackV1 autoBuild();

        public ContentPackV1 build() {
            version(ModelVersion.of(VERSION));
            return autoBuild();
        }
    }
}

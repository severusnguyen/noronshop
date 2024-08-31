package com.example.noronshopcommons.data.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

public abstract class BaseMap<Rq, Rp, Po> {

    @Named("toPOJO")
    public abstract Po toPOJO(Rq request);

    @IterableMapping(qualifiedByName = "toPOJO")
    public abstract List<Po> toPOJOs(List<Rq> requests);

    @Named("toResponse")
    public abstract Rp toResponse(Po pojo);

    @IterableMapping(qualifiedByName = "toResponse")
    public abstract List<Rp> toResponses(List<Po> pojos);

    public OffsetDateTime map(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    public LocalDateTime map(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDateTime();
    }
}

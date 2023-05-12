package com.stc.system.util;

import com.stc.system.dtos.ItemPermissionDTO;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author AhmedHakeem on 5/12/2023
 */
@Service
public class ItemPermissionMapper implements Function<Tuple, ItemPermissionDTO> {
    @Override
    public ItemPermissionDTO apply(Tuple tuple) {
      return   new ItemPermissionDTO(
              tuple.get(0, Long.class),
              tuple.get(1, String.class),
              tuple.get(2, Long.class));
    }
}

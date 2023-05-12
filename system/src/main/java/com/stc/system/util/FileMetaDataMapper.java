package com.stc.system.util;

import com.stc.system.dtos.FileMetaDataResponse;
import com.stc.system.dtos.ItemPermissionDTO;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author AhmedHakeem
 */
@Service
public class FileMetaDataMapper implements Function<Tuple, FileMetaDataResponse> {

    @Override
    public FileMetaDataResponse apply(Tuple tuple) {
      return   new FileMetaDataResponse(
              tuple.get(0, Long.class),
              tuple.get(1, Long.class),
              tuple.get(2, Long.class),
              tuple.get(3, String.class),
              tuple.get(4, String.class),
              tuple.get(5, String.class),
              tuple.get(6, String.class)
      );
    }
}

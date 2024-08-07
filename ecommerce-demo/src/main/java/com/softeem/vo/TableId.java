package com.softeem.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *
 *  ids: [{id:1},{id:2},{id:3}]
 *
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableId {
    private List<Id> ids;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id{
        private Long id;
    }
}

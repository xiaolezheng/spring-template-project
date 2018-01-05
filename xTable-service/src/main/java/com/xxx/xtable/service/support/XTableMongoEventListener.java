package com.xxx.xtable.service.support;

import com.xxx.xtable.api.model.XTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class XTableMongoEventListener extends AbstractMongoEventListener<XTable> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<XTable> event) {

    }

    @Override
    public void onAfterLoad(AfterLoadEvent<XTable> event) {

    }
}

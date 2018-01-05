package com.xxx.xtable.service.support;

import com.xxx.xtable.api.model.XTableData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class XTableDataMongoEventListener extends AbstractMongoEventListener<XTableData> {
    @Override
    public void onBeforeSave(BeforeSaveEvent<XTableData> event) {
    }

    @Override
    public void onAfterSave(AfterSaveEvent<XTableData> event) {
    }

    @Override
    public void onAfterLoad(AfterLoadEvent<XTableData> event) {
    }
}

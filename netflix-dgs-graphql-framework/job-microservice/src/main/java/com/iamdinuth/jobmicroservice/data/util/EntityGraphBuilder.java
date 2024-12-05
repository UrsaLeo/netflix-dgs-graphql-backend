package com.iamdinuth.jobmicroservice.data.util;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import graphql.schema.SelectedField;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityGraphBuilder {
    public EntityGraph build(DgsDataFetchingEnvironment dfe) {
        DynamicEntityGraph.Builder entityGraphBuilder = DynamicEntityGraph.fetching();
        String lastTransientFieldName = null;
        for (SelectedField fld : dfe.getSelectionSet().getFields()) {
            if (fld.getFieldDefinitions().get(0).getAppliedDirective("transient") == null){
                if (lastTransientFieldName == null || fld.getQualifiedName().startsWith(lastTransientFieldName+"/") == false){
                    entityGraphBuilder.addPath(fld.getQualifiedName().replace("/","."));
                }
            } else {
                lastTransientFieldName = fld.getQualifiedName();
            }
        }
        return entityGraphBuilder.build();
    }
}

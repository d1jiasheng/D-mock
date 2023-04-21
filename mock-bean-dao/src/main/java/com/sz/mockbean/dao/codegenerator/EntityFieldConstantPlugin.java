package com.sz.mockbean.dao.codegenerator;

import com.google.common.base.CaseFormat;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cuixiaojia@jd.com
 */
public class EntityFieldConstantPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        generateFieldConstants(introspectedTable, topLevelClass);
        return true;
    }

    private void generateFieldConstants(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
        List<Field> fields = topLevelClass.getFields();
        if (fields == null || fields.isEmpty()) {
            return;
        }

        List<Field> fieldConstants = new LinkedList<>();
        Iterator<Field> iterator = fields.iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (field.isStatic() || field.isFinal() || field.isTransient()
                    || !JavaVisibility.PRIVATE.equals(field.getVisibility())) {
                continue;
            }

            Field f = new Field(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field.getName()),
                    new FullyQualifiedJavaType("java.lang.String"));
            f.setVisibility(JavaVisibility.PUBLIC);
            f.setStatic(true);
            f.setFinal(true);
            f.setInitializationString("\"" + field.getName() + "\"");
            fieldConstants.add(f);
        }

        fieldConstants.forEach(fc -> {
            context.getCommentGenerator().addFieldComment(fc, introspectedTable);
            topLevelClass.addField(fc);
        });
    }

}

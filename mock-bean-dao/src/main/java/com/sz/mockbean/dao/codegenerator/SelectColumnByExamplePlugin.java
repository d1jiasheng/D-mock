package com.sz.mockbean.dao.codegenerator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author cuixiaojia@jd.com
 */
public class SelectColumnByExamplePlugin extends PluginAdapter {

    public static final String METHOD_NAME = "selectColumnByExample";
    public static final String ARG_NAME_COLUMNS = "columns";
    public static final String ARG_NAME_EXAMPLE = "example";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        addSelectColumnByExampleMethod(interfaze, introspectedTable);
        return super.clientGenerated(interfaze, introspectedTable);
    }

    private void addSelectColumnByExampleMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();

        Method method = new Method(METHOD_NAME);
        method.setAbstract(true);
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        FullyQualifiedJavaType listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(returnType);
        importedTypes.add(listType);
        returnType.addTypeArgument(listType);
        method.setReturnType(returnType);

        FullyQualifiedJavaType paramType = FullyQualifiedJavaType.getNewListInstance();
        paramType.addTypeArgument(FullyQualifiedJavaType.getStringInstance());
        importedTypes.add(paramType);
        method.addParameter(new Parameter(paramType, ARG_NAME_COLUMNS, "@Param(\"columns\")"));
        paramType = new FullyQualifiedJavaType(introspectedTable.getExampleType());
        importedTypes.add(paramType);
        method.addParameter(new Parameter(paramType, ARG_NAME_EXAMPLE, "@Param(\"example\")"));

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        addSelectColumnByExampleXml(document, introspectedTable);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private void addSelectColumnByExampleXml(Document document, IntrospectedTable introspectedTable) {
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$
        answer.addAttribute(new Attribute("id", METHOD_NAME));
        answer.addAttribute(new Attribute("parameterType", "map")); //$NON-NLS-1$ //$NON-NLS-2$
        answer.addAttribute(new Attribute(
                "resultMap", introspectedTable.getBaseResultMapId())); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        answer.addElement(new TextElement("select")); //$NON-NLS-1$
        answer.addElement(genForEachColumnsElement());
        StringBuilder sb = new StringBuilder();
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getExampleIncludeElement(introspectedTable));

        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "example.orderByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("order by ${example.orderByClause}")); //$NON-NLS-1$
        answer.addElement(ifElement);

        document.getRootElement().addElement(answer);
    }

    private XmlElement genForEachColumnsElement() {
        XmlElement forEachElement = new XmlElement("foreach"); //$NON-NLS-1$
        forEachElement.addAttribute(new Attribute("collection", ARG_NAME_COLUMNS)); //$NON-NLS-1$
        forEachElement.addAttribute(new Attribute("item", "col")); //$NO
        forEachElement.addAttribute(new Attribute("separator", ",")); //$NO
        forEachElement.addElement(new TextElement("${col}")); //$NON-NLS-1$
        return forEachElement;
    }

    private XmlElement getExampleIncludeElement(IntrospectedTable introspectedTable) {
        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "_parameter != null")); //$NON-NLS-1$ //$NON-NLS-2$
        XmlElement includeElement = new XmlElement("include"); //$NON-NLS-1$
        includeElement.addAttribute(new Attribute("refid", //$NON-NLS-1$
                introspectedTable.getMyBatis3UpdateByExampleWhereClauseId()));
        ifElement.addElement(includeElement);
        return ifElement;
    }

}

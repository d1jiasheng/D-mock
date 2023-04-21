package com.sz.mockbean.dao.codegenerator;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.codegen.mybatis3.javamapper.JavaMapperGenerator;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * @author cuixiaojia@jd.com
 */
public class CustomJavaMapperGenerator extends JavaMapperGenerator {

    public CustomJavaMapperGenerator() {
        super("src/main/java");
    }

    public CustomJavaMapperGenerator(String project) {
        super(project);
    }

    public CustomJavaMapperGenerator(String project, boolean requiresMatchedXMLGenerator) {
        super(project, requiresMatchedXMLGenerator);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        return super.getCompilationUnits()
                .stream()
                .filter(cu -> {
                    String targetJavaType = cu.getType().getShortNameWithoutTypeArguments();
                    if (targetJavaType.endsWith("Mapper")) {
                        String targetProject = getProject();
                        String targetPackage = cu.getType().getPackageName();
                        return getDirectory(targetProject, targetPackage).map(d -> {
                            String targetJavaMapper = targetJavaType + ".java";
                            File targetJavaFile = new File(d, targetJavaMapper);
                            return !targetJavaFile.exists();
                        }).orElse(true);
                    } else {
                        return true;
                    }
                }).collect(Collectors.toList());
    }

    private Optional<File> getDirectory(String targetProject, String targetPackage) {
        File project = new File(targetProject);
        if (!project.isDirectory()) {
            return Optional.empty();
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, ".");
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                return Optional.empty();
            }
        }

        return Optional.of(directory);
    }

}

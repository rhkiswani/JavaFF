package io.github.rhkiswani.javaff.detector;

import io.github.rhkiswani.javaff.beans.ValuesHolder;
import io.github.rhkiswani.javaff.lang.annotations.EqualsField;

/**
 * @author Mohamed Kiswani
 * @email rhkiswani@gmail.com
 * @url https://github.com/rhkiswani
 * @since 0.0.1
 *
 */
public class ApiMetadata extends ValuesHolder<ApiMetadata>{

    private String name;
    @EqualsField
    private String groupId;
    @EqualsField
    private String mainClassName;
    private String frameworkUrl;

    public ApiMetadata(){

    }

    public ApiMetadata(String groupId, String mainClassName){
        this(null, groupId, mainClassName, null);
    }

    public ApiMetadata(String name, String groupId, String mainClassName, String frameworkUrl){
        this.name = name;
        this.groupId = groupId;
        this.mainClassName = mainClassName;
        this.frameworkUrl = frameworkUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMainClassName() {
        return mainClassName;
    }

    public void setMainClassName(String mainClassName) {
        this.mainClassName = mainClassName;
    }

    public String getFrameworkUrl() {
        return frameworkUrl;
    }

    public void setFrameworkUrl(String frameworkUrl) {
        this.frameworkUrl = frameworkUrl;
    }
}

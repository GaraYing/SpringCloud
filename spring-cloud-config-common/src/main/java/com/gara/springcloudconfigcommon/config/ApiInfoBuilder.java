package com.gara.springcloudconfigcommon.config;

import com.google.common.collect.Lists;
import springfox.documentation.builders.BuilderDefaults;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;

import java.util.List;

/**
 * ApiInfo类属性没有Setter方法，无法直接通过配置文件注入属性值。通过ApiInfoBuilder类注入属性值，再构造ApiInfo类。
 *
 */
@SuppressWarnings("rawtypes")
public class ApiInfoBuilder {

    private String title;
    private String description;
    private String termsOfServiceUrl;
    private ContactBuilder contact;
    private String license;
    private String licenseUrl;
    private String version;
	private List<VendorExtension> vendorExtensions = Lists.newArrayList();

    public ApiInfoBuilder() {
    }

    public ApiInfoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ApiInfoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ApiInfoBuilder termsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
        return this;
    }

    public ApiInfoBuilder version(String version) {
        this.version = version;
        return this;
    }

    public ApiInfoBuilder contact(ContactBuilder contact) {
        this.contact = contact;
        return this;
    }

    public ApiInfoBuilder license(String license) {
        this.license = license;
        return this;
    }

    public ApiInfoBuilder licenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
        return this;
    }

    public ApiInfoBuilder extensions(List<VendorExtension> extensions) {
        this.vendorExtensions.addAll(BuilderDefaults.nullToEmptyList(extensions));
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ContactBuilder getContact() {
        return contact;
    }

    public void setContact(ContactBuilder contact) {
        this.contact = contact;
    }

    public List<VendorExtension> getVendorExtensions() {
        return vendorExtensions;
    }

    public void setVendorExtensions(List<VendorExtension> vendorExtensions) {
        this.vendorExtensions = vendorExtensions;
    }

    public ApiInfo build() {
        return new ApiInfo(title, description, version, termsOfServiceUrl, contact.build(), license, licenseUrl, vendorExtensions);
    }

}

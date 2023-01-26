/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.artifactregistry.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import com.google.devtools.artifactregistry.v1.ArtifactRegistryClient;
import com.google.devtools.artifactregistry.v1.ArtifactRegistrySettings;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link ArtifactRegistryClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(ArtifactRegistryClient.class)
@ConditionalOnProperty(
    value = "com.google.devtools.artifactregistry.v1.artifact-registry.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(ArtifactRegistrySpringProperties.class)
public class ArtifactRegistrySpringAutoConfiguration {
  private final ArtifactRegistrySpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER =
      LogFactory.getLog(ArtifactRegistrySpringAutoConfiguration.class);

  protected ArtifactRegistrySpringAutoConfiguration(
      ArtifactRegistrySpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from ArtifactRegistry-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean. The default is gRPC and will default to it
   * unless the useRest option is provided to use HTTP transport instead
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultArtifactRegistryTransportChannelProvider")
  public TransportChannelProvider defaultArtifactRegistryTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return ArtifactRegistrySettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return ArtifactRegistrySettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a ArtifactRegistrySettings bean configured to use a DefaultCredentialsProvider and the
   * client library's default transport channel provider
   * (defaultArtifactRegistryTransportChannelProvider()). It also configures the quota project ID
   * and executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in ArtifactRegistrySpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link ArtifactRegistrySettings} bean configured with {@link
   *     TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public ArtifactRegistrySettings artifactRegistrySettings(
      @Qualifier("defaultArtifactRegistryTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    ArtifactRegistrySettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = ArtifactRegistrySettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = ArtifactRegistrySettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          ArtifactRegistrySettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings listDockerImagesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDockerImagesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listDockerImagesSettings()
          .setRetrySettings(listDockerImagesRetrySettings);

      RetrySettings getDockerImageRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDockerImageSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getDockerImageSettings().setRetrySettings(getDockerImageRetrySettings);

      RetrySettings listRepositoriesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listRepositoriesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listRepositoriesSettings()
          .setRetrySettings(listRepositoriesRetrySettings);

      RetrySettings getRepositoryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getRepositorySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getRepositorySettings().setRetrySettings(getRepositoryRetrySettings);

      RetrySettings updateRepositoryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateRepositorySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .updateRepositorySettings()
          .setRetrySettings(updateRepositoryRetrySettings);

      RetrySettings listPackagesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listPackagesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listPackagesSettings().setRetrySettings(listPackagesRetrySettings);

      RetrySettings getPackageRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getPackageSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getPackageSettings().setRetrySettings(getPackageRetrySettings);

      RetrySettings listVersionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listVersionsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listVersionsSettings().setRetrySettings(listVersionsRetrySettings);

      RetrySettings getVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getVersionSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getVersionSettings().setRetrySettings(getVersionRetrySettings);

      RetrySettings listFilesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listFilesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listFilesSettings().setRetrySettings(listFilesRetrySettings);

      RetrySettings getFileRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getFileSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getFileSettings().setRetrySettings(getFileRetrySettings);

      RetrySettings listTagsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTagsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listTagsSettings().setRetrySettings(listTagsRetrySettings);

      RetrySettings getTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTagSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getTagSettings().setRetrySettings(getTagRetrySettings);

      RetrySettings createTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createTagSettings().setRetrySettings(createTagRetrySettings);

      RetrySettings updateTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateTagSettings().setRetrySettings(updateTagRetrySettings);

      RetrySettings deleteTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteTagSettings().setRetrySettings(deleteTagRetrySettings);

      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);

      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);

      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);

      RetrySettings getProjectSettingsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getProjectSettingsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getProjectSettingsSettings()
          .setRetrySettings(getProjectSettingsRetrySettings);

      RetrySettings updateProjectSettingsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateProjectSettingsSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .updateProjectSettingsSettings()
          .setRetrySettings(updateProjectSettingsRetrySettings);

      RetrySettings listLocationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listLocationsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listLocationsSettings().setRetrySettings(listLocationsRetrySettings);

      RetrySettings getLocationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getLocationSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getLocationSettings().setRetrySettings(getLocationRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry listDockerImagesRetry = clientProperties.getListDockerImagesRetry();
    if (listDockerImagesRetry != null) {
      RetrySettings listDockerImagesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDockerImagesSettings().getRetrySettings(),
              listDockerImagesRetry);
      clientSettingsBuilder
          .listDockerImagesSettings()
          .setRetrySettings(listDockerImagesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listDockerImages from properties.");
      }
    }
    Retry getDockerImageRetry = clientProperties.getGetDockerImageRetry();
    if (getDockerImageRetry != null) {
      RetrySettings getDockerImageRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDockerImageSettings().getRetrySettings(),
              getDockerImageRetry);
      clientSettingsBuilder.getDockerImageSettings().setRetrySettings(getDockerImageRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getDockerImage from properties.");
      }
    }
    Retry listRepositoriesRetry = clientProperties.getListRepositoriesRetry();
    if (listRepositoriesRetry != null) {
      RetrySettings listRepositoriesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listRepositoriesSettings().getRetrySettings(),
              listRepositoriesRetry);
      clientSettingsBuilder
          .listRepositoriesSettings()
          .setRetrySettings(listRepositoriesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listRepositories from properties.");
      }
    }
    Retry getRepositoryRetry = clientProperties.getGetRepositoryRetry();
    if (getRepositoryRetry != null) {
      RetrySettings getRepositoryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getRepositorySettings().getRetrySettings(), getRepositoryRetry);
      clientSettingsBuilder.getRepositorySettings().setRetrySettings(getRepositoryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getRepository from properties.");
      }
    }
    Retry updateRepositoryRetry = clientProperties.getUpdateRepositoryRetry();
    if (updateRepositoryRetry != null) {
      RetrySettings updateRepositoryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateRepositorySettings().getRetrySettings(),
              updateRepositoryRetry);
      clientSettingsBuilder
          .updateRepositorySettings()
          .setRetrySettings(updateRepositoryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateRepository from properties.");
      }
    }
    Retry listPackagesRetry = clientProperties.getListPackagesRetry();
    if (listPackagesRetry != null) {
      RetrySettings listPackagesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listPackagesSettings().getRetrySettings(), listPackagesRetry);
      clientSettingsBuilder.listPackagesSettings().setRetrySettings(listPackagesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listPackages from properties.");
      }
    }
    Retry getPackageRetry = clientProperties.getGetPackageRetry();
    if (getPackageRetry != null) {
      RetrySettings getPackageRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getPackageSettings().getRetrySettings(), getPackageRetry);
      clientSettingsBuilder.getPackageSettings().setRetrySettings(getPackageRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getPackage from properties.");
      }
    }
    Retry listVersionsRetry = clientProperties.getListVersionsRetry();
    if (listVersionsRetry != null) {
      RetrySettings listVersionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listVersionsSettings().getRetrySettings(), listVersionsRetry);
      clientSettingsBuilder.listVersionsSettings().setRetrySettings(listVersionsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listVersions from properties.");
      }
    }
    Retry getVersionRetry = clientProperties.getGetVersionRetry();
    if (getVersionRetry != null) {
      RetrySettings getVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getVersionSettings().getRetrySettings(), getVersionRetry);
      clientSettingsBuilder.getVersionSettings().setRetrySettings(getVersionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getVersion from properties.");
      }
    }
    Retry listFilesRetry = clientProperties.getListFilesRetry();
    if (listFilesRetry != null) {
      RetrySettings listFilesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listFilesSettings().getRetrySettings(), listFilesRetry);
      clientSettingsBuilder.listFilesSettings().setRetrySettings(listFilesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listFiles from properties.");
      }
    }
    Retry getFileRetry = clientProperties.getGetFileRetry();
    if (getFileRetry != null) {
      RetrySettings getFileRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getFileSettings().getRetrySettings(), getFileRetry);
      clientSettingsBuilder.getFileSettings().setRetrySettings(getFileRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getFile from properties.");
      }
    }
    Retry listTagsRetry = clientProperties.getListTagsRetry();
    if (listTagsRetry != null) {
      RetrySettings listTagsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTagsSettings().getRetrySettings(), listTagsRetry);
      clientSettingsBuilder.listTagsSettings().setRetrySettings(listTagsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listTags from properties.");
      }
    }
    Retry getTagRetry = clientProperties.getGetTagRetry();
    if (getTagRetry != null) {
      RetrySettings getTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTagSettings().getRetrySettings(), getTagRetry);
      clientSettingsBuilder.getTagSettings().setRetrySettings(getTagRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getTag from properties.");
      }
    }
    Retry createTagRetry = clientProperties.getCreateTagRetry();
    if (createTagRetry != null) {
      RetrySettings createTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagSettings().getRetrySettings(), createTagRetry);
      clientSettingsBuilder.createTagSettings().setRetrySettings(createTagRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createTag from properties.");
      }
    }
    Retry updateTagRetry = clientProperties.getUpdateTagRetry();
    if (updateTagRetry != null) {
      RetrySettings updateTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagSettings().getRetrySettings(), updateTagRetry);
      clientSettingsBuilder.updateTagSettings().setRetrySettings(updateTagRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateTag from properties.");
      }
    }
    Retry deleteTagRetry = clientProperties.getDeleteTagRetry();
    if (deleteTagRetry != null) {
      RetrySettings deleteTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagSettings().getRetrySettings(), deleteTagRetry);
      clientSettingsBuilder.deleteTagSettings().setRetrySettings(deleteTagRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteTag from properties.");
      }
    }
    Retry setIamPolicyRetry = clientProperties.getSetIamPolicyRetry();
    if (setIamPolicyRetry != null) {
      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), setIamPolicyRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for setIamPolicy from properties.");
      }
    }
    Retry getIamPolicyRetry = clientProperties.getGetIamPolicyRetry();
    if (getIamPolicyRetry != null) {
      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), getIamPolicyRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getIamPolicy from properties.");
      }
    }
    Retry testIamPermissionsRetry = clientProperties.getTestIamPermissionsRetry();
    if (testIamPermissionsRetry != null) {
      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(),
              testIamPermissionsRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for testIamPermissions from properties.");
      }
    }
    Retry getProjectSettingsRetry = clientProperties.getGetProjectSettingsRetry();
    if (getProjectSettingsRetry != null) {
      RetrySettings getProjectSettingsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getProjectSettingsSettings().getRetrySettings(),
              getProjectSettingsRetry);
      clientSettingsBuilder
          .getProjectSettingsSettings()
          .setRetrySettings(getProjectSettingsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getProjectSettings from properties.");
      }
    }
    Retry updateProjectSettingsRetry = clientProperties.getUpdateProjectSettingsRetry();
    if (updateProjectSettingsRetry != null) {
      RetrySettings updateProjectSettingsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateProjectSettingsSettings().getRetrySettings(),
              updateProjectSettingsRetry);
      clientSettingsBuilder
          .updateProjectSettingsSettings()
          .setRetrySettings(updateProjectSettingsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateProjectSettings from properties.");
      }
    }
    Retry listLocationsRetry = clientProperties.getListLocationsRetry();
    if (listLocationsRetry != null) {
      RetrySettings listLocationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listLocationsSettings().getRetrySettings(), listLocationsRetry);
      clientSettingsBuilder.listLocationsSettings().setRetrySettings(listLocationsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listLocations from properties.");
      }
    }
    Retry getLocationRetry = clientProperties.getGetLocationRetry();
    if (getLocationRetry != null) {
      RetrySettings getLocationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getLocationSettings().getRetrySettings(), getLocationRetry);
      clientSettingsBuilder.getLocationSettings().setRetrySettings(getLocationRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getLocation from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a ArtifactRegistryClient bean configured with ArtifactRegistrySettings.
   *
   * @param artifactRegistrySettings settings to configure an instance of client bean.
   * @return a {@link ArtifactRegistryClient} bean configured with {@link ArtifactRegistrySettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public ArtifactRegistryClient artifactRegistryClient(
      ArtifactRegistrySettings artifactRegistrySettings) throws IOException {
    return ArtifactRegistryClient.create(artifactRegistrySettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-artifact-registry";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}

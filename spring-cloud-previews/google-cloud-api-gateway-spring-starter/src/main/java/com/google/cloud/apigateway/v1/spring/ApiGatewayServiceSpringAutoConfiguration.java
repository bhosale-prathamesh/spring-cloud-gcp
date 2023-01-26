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

package com.google.cloud.apigateway.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.apigateway.v1.ApiGatewayServiceClient;
import com.google.cloud.apigateway.v1.ApiGatewayServiceSettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
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
 * Auto-configuration for {@link ApiGatewayServiceClient}.
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
@ConditionalOnClass(ApiGatewayServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.apigateway.v1.api-gateway-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(ApiGatewayServiceSpringProperties.class)
public class ApiGatewayServiceSpringAutoConfiguration {
  private final ApiGatewayServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER =
      LogFactory.getLog(ApiGatewayServiceSpringAutoConfiguration.class);

  protected ApiGatewayServiceSpringAutoConfiguration(
      ApiGatewayServiceSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from ApiGatewayService-specific configuration");
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
  @ConditionalOnMissingBean(name = "defaultApiGatewayServiceTransportChannelProvider")
  public TransportChannelProvider defaultApiGatewayServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return ApiGatewayServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return ApiGatewayServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a ApiGatewayServiceSettings bean configured to use a DefaultCredentialsProvider and
   * the client library's default transport channel provider
   * (defaultApiGatewayServiceTransportChannelProvider()). It also configures the quota project ID
   * and executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in ApiGatewayServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link ApiGatewayServiceSettings} bean configured with {@link
   *     TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public ApiGatewayServiceSettings apiGatewayServiceSettings(
      @Qualifier("defaultApiGatewayServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    ApiGatewayServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = ApiGatewayServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = ApiGatewayServiceSettings.newBuilder();
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
          ApiGatewayServiceSettings.defaultExecutorProviderBuilder()
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
      RetrySettings listGatewaysRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listGatewaysSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listGatewaysSettings().setRetrySettings(listGatewaysRetrySettings);

      RetrySettings getGatewayRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getGatewaySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getGatewaySettings().setRetrySettings(getGatewayRetrySettings);

      RetrySettings listApisRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listApisSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listApisSettings().setRetrySettings(listApisRetrySettings);

      RetrySettings getApiRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getApiSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getApiSettings().setRetrySettings(getApiRetrySettings);

      RetrySettings listApiConfigsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listApiConfigsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listApiConfigsSettings().setRetrySettings(listApiConfigsRetrySettings);

      RetrySettings getApiConfigRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getApiConfigSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getApiConfigSettings().setRetrySettings(getApiConfigRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry listGatewaysRetry = clientProperties.getListGatewaysRetry();
    if (listGatewaysRetry != null) {
      RetrySettings listGatewaysRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listGatewaysSettings().getRetrySettings(), listGatewaysRetry);
      clientSettingsBuilder.listGatewaysSettings().setRetrySettings(listGatewaysRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listGateways from properties.");
      }
    }
    Retry getGatewayRetry = clientProperties.getGetGatewayRetry();
    if (getGatewayRetry != null) {
      RetrySettings getGatewayRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getGatewaySettings().getRetrySettings(), getGatewayRetry);
      clientSettingsBuilder.getGatewaySettings().setRetrySettings(getGatewayRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getGateway from properties.");
      }
    }
    Retry listApisRetry = clientProperties.getListApisRetry();
    if (listApisRetry != null) {
      RetrySettings listApisRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listApisSettings().getRetrySettings(), listApisRetry);
      clientSettingsBuilder.listApisSettings().setRetrySettings(listApisRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listApis from properties.");
      }
    }
    Retry getApiRetry = clientProperties.getGetApiRetry();
    if (getApiRetry != null) {
      RetrySettings getApiRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getApiSettings().getRetrySettings(), getApiRetry);
      clientSettingsBuilder.getApiSettings().setRetrySettings(getApiRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getApi from properties.");
      }
    }
    Retry listApiConfigsRetry = clientProperties.getListApiConfigsRetry();
    if (listApiConfigsRetry != null) {
      RetrySettings listApiConfigsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listApiConfigsSettings().getRetrySettings(),
              listApiConfigsRetry);
      clientSettingsBuilder.listApiConfigsSettings().setRetrySettings(listApiConfigsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listApiConfigs from properties.");
      }
    }
    Retry getApiConfigRetry = clientProperties.getGetApiConfigRetry();
    if (getApiConfigRetry != null) {
      RetrySettings getApiConfigRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getApiConfigSettings().getRetrySettings(), getApiConfigRetry);
      clientSettingsBuilder.getApiConfigSettings().setRetrySettings(getApiConfigRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getApiConfig from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a ApiGatewayServiceClient bean configured with ApiGatewayServiceSettings.
   *
   * @param apiGatewayServiceSettings settings to configure an instance of client bean.
   * @return a {@link ApiGatewayServiceClient} bean configured with {@link
   *     ApiGatewayServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public ApiGatewayServiceClient apiGatewayServiceClient(
      ApiGatewayServiceSettings apiGatewayServiceSettings) throws IOException {
    return ApiGatewayServiceClient.create(apiGatewayServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-api-gateway-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}

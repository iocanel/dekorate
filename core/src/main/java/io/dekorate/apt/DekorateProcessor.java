/**
 * Copyright 2018 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.dekorate.apt;

import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import io.dekorate.Logger;
import io.dekorate.LoggerFactory;
import io.dekorate.adapter.DekorateConfigAdapter;
import io.dekorate.annotation.Dekorate;
import io.dekorate.config.DekorateConfig;
import io.dekorate.processor.AbstractAnnotationProcessor;

/**
 * Detects @Dekorate and configures application based on the specified configuration files.
 */
@SupportedAnnotationTypes({ "io.dekorate.annotation.Dekorate" })
public class DekorateProcessor extends AbstractAnnotationProcessor {

  private static final String[] DEFAULT_CONFIG_FILES = { "application.properties", "application.yml" };
  private final Logger LOGGER = LoggerFactory.getLogger();

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      getSession().close();
      return true;
    }

    for (TypeElement typeElement : annotations) {
      for (Element mainClass : roundEnv.getElementsAnnotatedWith(typeElement)) {
        LOGGER.info("Found @Dekorate on: " + mainClass.toString());
        Dekorate dekorate = mainClass.getAnnotation(Dekorate.class);
        DekorateConfig dekorateConfig = DekorateConfigAdapter.adapt(dekorate);
        String[] configFiles = dekorateConfig.getResources().length > 0 ? dekorateConfig.getResources()
            : DEFAULT_CONFIG_FILES;
        getSession().addPropertyConfiguration(readApplicationConfig(configFiles));
      }
    }
    return false;
  }
}

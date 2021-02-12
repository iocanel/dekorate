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

package io.dekorate.kubernetes.decorator;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ServiceAccountFluent;

/**
 * Associate an existing ServiceAccount with the specified Secret.
 */
public class AddSecretToServiceAccountDecorator extends NamedResourceDecorator<ServiceAccountFluent<?>> {

  private final String secret;

  public AddSecretToServiceAccountDecorator(String secret) {
    this(ANY, secret);
  }

  public AddSecretToServiceAccountDecorator(String name, String secret) {
    super(name);
    this.secret = secret;
  }

  @Override
  public void andThenVisit(ServiceAccountFluent<?> serviceAccount, ObjectMeta resourceMeta) {
    serviceAccount.addNewSecret()
        .withKind("Secret")
        .withName(secret)
        .endSecret();
  }
}

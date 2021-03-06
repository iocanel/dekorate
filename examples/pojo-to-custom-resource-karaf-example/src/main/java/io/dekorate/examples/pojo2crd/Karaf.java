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

package io.dekorate.examples.pojo2crd;

import io.dekorate.crd.annotation.CustomResource;
import io.dekorate.crd.annotation.Status;
import io.dekorate.crd.config.Scope;

@CustomResource(group = "io.karaf", version = "v1", scope = Scope.Namespaced, status = KarafStatus.class)
public class Karaf {

    private String name;
    private List<String> repoistory;
    private List<String> features;
    private List<String> bundles;
    private Map<String, String> config;
}


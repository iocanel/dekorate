
package io.dekorate.tekton.configurator;

import io.dekorate.kubernetes.config.Configurator;
import io.dekorate.tekton.config.TektonConfigFluent;
import io.dekorate.utils.Strings;

/**
 * Apply the specified tekoton image push service account to the configuration.
 */
public class ApplyTektonImagePushServiceAccountConfigurator extends Configurator<TektonConfigFluent<?>> {

  private final String serviceAccount;
  
	public ApplyTektonImagePushServiceAccountConfigurator(String serviceAccount) {
		this.serviceAccount = serviceAccount;
	}

	@Override
	public void visit(TektonConfigFluent<?> config) {
    if (Strings.isNotNullOrEmpty(serviceAccount)) {
      config.withImagePushServiceAccount(serviceAccount);
    }
	}
}

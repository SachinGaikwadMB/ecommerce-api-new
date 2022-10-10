package com.mb.api.configs;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AudiorAwareImpl implements AuditorAware<String>
{

	@Override
	public Optional<String> getCurrentAuditor()
	{
		return Optional.of("Sachin Gaikwad");
	}

}

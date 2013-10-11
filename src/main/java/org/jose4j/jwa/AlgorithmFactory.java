/*
 * Copyright 2012-2013 Brian Campbell
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
 */

package org.jose4j.jwa;

import org.jose4j.lang.JoseException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 */
public class AlgorithmFactory<A extends Algorithm>
{
    private String parameterName;

    private final Map<String,A> algorithms = new LinkedHashMap<String, A>();

    public AlgorithmFactory(String parameterName)
    {
        this.parameterName = parameterName;
    }

    public A getAlgorithm(String algorithmIdentifier) throws JoseException
    {
        A algo = algorithms.get(algorithmIdentifier);
        
        if (algo == null)
        {
            throw new JoseException(algorithmIdentifier + " is an unknown or unsupported "+parameterName+" algorithm (not one of " + getSupportedAlgorithms() + ").");
        }
        
        return algo;
    }

    public Set<String> getSupportedAlgorithms()
    {
        return Collections.unmodifiableSet(algorithms.keySet());
    }

    public void registerAlgorithm(A algorithm)
    {
        algorithms.put(algorithm.getAlgorithmIdentifier(), algorithm);
    }

    public void unregisterAlgorithm(String algorithmIdentifier)
    {
        algorithms.remove(algorithmIdentifier);
    }
}

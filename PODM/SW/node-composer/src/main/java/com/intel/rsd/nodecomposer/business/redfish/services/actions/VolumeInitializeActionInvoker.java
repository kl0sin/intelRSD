/*
 * Copyright (c) 2015-2019 Intel Corporation
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

package com.intel.rsd.nodecomposer.business.redfish.services.actions;

import com.intel.rsd.nodecomposer.business.EntityOperationException;
import com.intel.rsd.nodecomposer.business.dto.actions.InitializeActionDto;
import com.intel.rsd.nodecomposer.persistence.redfish.Volume;
import com.intel.rsd.nodecomposer.types.InitializeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.URI;

import static com.intel.rsd.nodecomposer.types.redfish.ActionsResourceNames.ACTIONS_VOLUME_INITIALIZE;
import static javax.transaction.Transactional.TxType.MANDATORY;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@Component
@Scope(SCOPE_SINGLETON)
public class VolumeInitializeActionInvoker {
    private final RestRequestInvoker restRequestInvoker;

    @Autowired
    public VolumeInitializeActionInvoker(RestRequestInvoker restRequestInvoker) {
        this.restRequestInvoker = restRequestInvoker;
    }

    @Transactional(MANDATORY)
    public void initialize(Volume volume, InitializeType initializeType) throws EntityOperationException {
        URI initializeUri = fromUri(volume.getUri().toUri()).pathSegment(ACTIONS_VOLUME_INITIALIZE).build().toUri();

        restRequestInvoker.post(initializeUri, new InitializeActionDto(initializeType));
    }
}

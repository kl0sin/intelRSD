/*
 * Copyright (c) 2017-2019 Intel Corporation
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

package com.intel.rsd.nodecomposer.composition.allocation.strategy;

import com.intel.rsd.nodecomposer.composition.assembly.tasks.ChangeTpmStateTask;
import com.intel.rsd.nodecomposer.persistence.redfish.ComputerSystem;
import com.intel.rsd.nodecomposer.types.actions.ChangeTpmStatusUpdateDefinition;
import com.intel.rsd.nodecomposer.utils.beans.NodeComposerBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class ChangeTpmStateTaskFactory {
    private final NodeComposerBeanFactory beanFactory;

    @Autowired
    public ChangeTpmStateTaskFactory(NodeComposerBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Transactional(SUPPORTS)
    public ChangeTpmStateTask createChangeTpmStateTask(ComputerSystem computerSystem,
                                                       ChangeTpmStatusUpdateDefinition changeTpmStatusUpdateDefinition) {
        ChangeTpmStateTask changeTpmStateTask = beanFactory.create(ChangeTpmStateTask.class).init(computerSystem.getUri());
        changeTpmStateTask.setChangeTpmStatusUpdateDefinition(changeTpmStatusUpdateDefinition);
        return changeTpmStateTask;
    }
}

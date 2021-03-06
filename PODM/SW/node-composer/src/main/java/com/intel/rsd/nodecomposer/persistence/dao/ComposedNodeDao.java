/*
 * Copyright (c) 2016-2019 Intel Corporation
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

package com.intel.rsd.nodecomposer.persistence.dao;

import com.intel.rsd.nodecomposer.business.services.redfish.odataid.ODataId;
import com.intel.rsd.nodecomposer.persistence.redfish.ComposedNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.intel.rsd.collections.IterableHelper.optionalSingle;
import static com.intel.rsd.nodecomposer.persistence.redfish.ComposedNode.GET_ALL_NODES_URIS;
import static com.intel.rsd.nodecomposer.persistence.redfish.ComposedNode.GET_NODES_ELIGIBLE_FOR_RECOVERY;
import static com.intel.rsd.nodecomposer.persistence.redfish.ComposedNode.GET_NODE_MATCHING_RELATED_SYSTEM_UUID;
import static javax.transaction.Transactional.TxType.MANDATORY;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component
@Scope(SCOPE_SINGLETON)
public class ComposedNodeDao extends Dao<ComposedNode> {
    @Transactional(MANDATORY)
    public List<ComposedNode> getComposedNodesEligibleForRecovery() {
        return entityManager.createNamedQuery(GET_NODES_ELIGIBLE_FOR_RECOVERY, ComposedNode.class).getResultList();
    }

    @Transactional(MANDATORY)
    public Optional<ComposedNode> tryFindComposedNodeByComputerSystemUuid(UUID uuid) {
        TypedQuery<ComposedNode> query = entityManager.createNamedQuery(GET_NODE_MATCHING_RELATED_SYSTEM_UUID, ComposedNode.class);
        query.setParameter("uuid", uuid);
        return optionalSingle(query.getResultList());
    }

    @Transactional(MANDATORY)
    public List<ODataId> getAllComposedNodeUris() {
        return entityManager.createNamedQuery(GET_ALL_NODES_URIS, ODataId.class).getResultList();
    }

    @Transactional(REQUIRED)
    public void delete(ODataId oDataId) {
        tryFind(oDataId).ifPresent(this::remove);
    }
}

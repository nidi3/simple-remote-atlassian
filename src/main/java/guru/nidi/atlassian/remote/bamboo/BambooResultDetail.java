/*
 * Copyright (C) 2013 Stefan Niederhauser (nidin@gmx.ch)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package guru.nidi.atlassian.remote.bamboo;

import guru.nidi.atlassian.remote.bamboo.rest.BambooPlan;
import guru.nidi.atlassian.remote.bamboo.rest.ResultLifecycleState;
import guru.nidi.atlassian.remote.bamboo.rest.ResultState;

import java.util.Date;

/**
 *
 */
public class BambooResultDetail {
    private BambooPlan plan;
    private String projectName;
    private ResultLifecycleState lifeCycleState;
    private long id;
    private Date buildStartedTime;
    private Date buildCompletedTime;
    private int buildDurationInSeconds;
    private int successfulTestCount;
    private int failedTestCount;
    private int quarantinedTestCount;
    private String buildReason;
    private String key;
    private ResultState state;
    private int number;

    public BambooPlan getPlan() {
        return plan;
    }

    public void setPlan(BambooPlan plan) {
        this.plan = plan;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ResultLifecycleState getLifeCycleState() {
        return lifeCycleState;
    }

    public void setLifeCycleState(ResultLifecycleState lifeCycleState) {
        this.lifeCycleState = lifeCycleState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBuildStartedTime() {
        return buildStartedTime;
    }

    public void setBuildStartedTime(Date buildStartedTime) {
        this.buildStartedTime = buildStartedTime;
    }

    public Date getBuildCompletedTime() {
        return buildCompletedTime;
    }

    public void setBuildCompletedTime(Date buildCompletedTime) {
        this.buildCompletedTime = buildCompletedTime;
    }

    public int getBuildDurationInSeconds() {
        return buildDurationInSeconds;
    }

    public void setBuildDurationInSeconds(int buildDurationInSeconds) {
        this.buildDurationInSeconds = buildDurationInSeconds;
    }

    public int getSuccessfulTestCount() {
        return successfulTestCount;
    }

    public void setSuccessfulTestCount(int successfulTestCount) {
        this.successfulTestCount = successfulTestCount;
    }

    public int getFailedTestCount() {
        return failedTestCount;
    }

    public void setFailedTestCount(int failedTestCount) {
        this.failedTestCount = failedTestCount;
    }

    public int getQuarantinedTestCount() {
        return quarantinedTestCount;
    }

    public void setQuarantinedTestCount(int quarantinedTestCount) {
        this.quarantinedTestCount = quarantinedTestCount;
    }

    public String getBuildReason() {
        return buildReason;
    }

    public void setBuildReason(String buildReason) {
        this.buildReason = buildReason;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ResultState getState() {
        return state;
    }

    public void setState(ResultState state) {
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

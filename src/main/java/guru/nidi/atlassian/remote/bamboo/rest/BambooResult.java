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
package guru.nidi.atlassian.remote.bamboo.rest;

/**
 *
 */
public class BambooResult {
    private ResultLifecycleState lifeCycleState;
    private long id;
    private String key;
    private ResultState state;
    private int number;
    private BambooPlan plan;

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

    public BambooPlan getPlan() {
        return plan;
    }

    public void setPlan(BambooPlan plan) {
        this.plan = plan;
    }
}

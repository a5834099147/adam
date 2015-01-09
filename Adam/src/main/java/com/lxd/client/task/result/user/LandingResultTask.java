/*
 * Copyright (C) 2014 a5834099147(lxd) <a5834099147@126.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.lxd.client.task.result.user;

import com.lxd.client.handle.HandleResource;
import com.lxd.client.task.ClientTask;
import com.lxd.client.view.control.UiSingleton;

/**
 * 登陆结果处理
 * 
 * @author: a5834099147
 * @mailto: a5834099147@126.com
 * @date: 2015年1月9日
 * @blog : http://a5834099147.github.io/
 * @review
 */
public class LandingResultTask extends ClientTask {

    private boolean success;
    private String  error_msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @Override
    public void execute() {
        if (success) {
           HandleResource.getSingleton().getLogin().loginSuccess(UiSingleton.getSingleton().getUser());
        } else {
            HandleResource.getSingleton().getLogin().loginFail(error_msg);
        }
    }

}

/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { BaseResponse_Page_EntryVO_ } from '../models/BaseResponse_Page_EntryVO_';
import type { BaseResponse_Page_UserVO_ } from '../models/BaseResponse_Page_UserVO_';
import type { BaseResponse_UserVO_ } from '../models/BaseResponse_UserVO_';
import type { UserEditRequest } from '../models/UserEditRequest';
import type { UserLoginRequest } from '../models/UserLoginRequest';
import type { UserRegisterRequest } from '../models/UserRegisterRequest';
import type { CancelablePromise } from '@/api/core/CancelablePromise';
import { OpenAPI } from '@/api/core/OpenAPI';
import { request as __request } from '@/api/core/request';
export class UserControllerService {
    /**
     * userList
     * @param createTime
     * @param current
     * @param gender
     * @param id
     * @param isDelete
     * @param pageSize
     * @param role
     * @param sortField
     * @param sortOrder
     * @param status
     * @param updateTime
     * @param userAccount
     * @param userName
     * @returns BaseResponse_Page_UserVO_ OK
     * @throws ApiError
     */
    public static userListUsingGet1(
        createTime?: string,
        current?: number,
        gender?: number,
        id?: number,
        isDelete?: number,
        pageSize?: number,
        role?: number,
        sortField?: string,
        sortOrder?: string,
        status?: number,
        updateTime?: string,
        userAccount?: string,
        userName?: string,
    ): CancelablePromise<BaseResponse_Page_UserVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/user',
            query: {
                'createTime': createTime,
                'current': current,
                'gender': gender,
                'id': id,
                'isDelete': isDelete,
                'pageSize': pageSize,
                'role': role,
                'sortField': sortField,
                'sortOrder': sortOrder,
                'status': status,
                'updateTime': updateTime,
                'userAccount': userAccount,
                'userName': userName,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * updateUser
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static updateUserUsingPut1(
        requestBody?: UserEditRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/user',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * getUserEntryList
     * @param current
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @returns BaseResponse_Page_EntryVO_ OK
     * @throws ApiError
     */
    public static getUserEntryListUsingGet(
        current?: number,
        pageSize?: number,
        sortField?: string,
        sortOrder?: string,
    ): CancelablePromise<BaseResponse_Page_EntryVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/user/entry',
            query: {
                'current': current,
                'pageSize': pageSize,
                'sortField': sortField,
                'sortOrder': sortOrder,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * getLoginUser
     * @returns BaseResponse_UserVO_ OK
     * @throws ApiError
     */
    public static getLoginUserUsingGet(): CancelablePromise<BaseResponse_UserVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/user/login',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * userLogin
     * @param requestBody
     * @returns BaseResponse_UserVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static userLoginUsingPost(
        requestBody?: UserLoginRequest,
    ): CancelablePromise<BaseResponse_UserVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/user/login',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * userLogout
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static userLogoutUsingPost(): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/user/logout',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * userRegister
     * @param requestBody
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static userRegisterUsingPost(
        requestBody?: UserRegisterRequest,
    ): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/user/register',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
}

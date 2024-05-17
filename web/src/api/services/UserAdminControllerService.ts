/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { BaseResponse_Page_User_ } from '../models/BaseResponse_Page_User_';
import type { BaseResponse_User_ } from '../models/BaseResponse_User_';
import type { UserAddRequest } from '../models/UserAddRequest';
import type { UserUpdateRequest } from '../models/UserUpdateRequest';
import type { CancelablePromise } from '@/api/core/CancelablePromise';
import { OpenAPI } from '@/api/core/OpenAPI';
import { request as __request } from '@/api/core/request';
export class UserAdminControllerService {
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
     * @returns BaseResponse_Page_User_ OK
     * @throws ApiError
     */
    public static userListUsingGet(
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
    ): CancelablePromise<BaseResponse_Page_User_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/admin/user',
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
    public static updateUserUsingPut(
        requestBody?: UserUpdateRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/admin/user',
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
     * addUser
     * @param requestBody
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addUserUsingPost(
        requestBody?: UserAddRequest,
    ): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/admin/user',
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
     * getUserById
     * @param userId userId
     * @returns BaseResponse_User_ OK
     * @throws ApiError
     */
    public static getUserByIdUsingGet(
        userId: number,
    ): CancelablePromise<BaseResponse_User_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/admin/user/{userId}',
            path: {
                'userId': userId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * deleteUser
     * @param userId userId
     * @returns BaseResponse_boolean_ OK
     * @throws ApiError
     */
    public static deleteUserUsingDelete(
        userId: number,
    ): CancelablePromise<BaseResponse_boolean_> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/admin/user/{userId}',
            path: {
                'userId': userId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
            },
        });
    }
}

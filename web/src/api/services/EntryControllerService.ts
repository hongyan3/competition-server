/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_EntryVO_ } from '../models/BaseResponse_EntryVO_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { BaseResponse_Page_EntryCommentVO_ } from '../models/BaseResponse_Page_EntryCommentVO_';
import type { BaseResponse_Page_EntryVO_ } from '../models/BaseResponse_Page_EntryVO_';
import type { EntryAddRequest } from '../models/EntryAddRequest';
import type { EntryCommentAddRequest } from '../models/EntryCommentAddRequest';
import type { EntryEditRequest } from '../models/EntryEditRequest';
import type { EntryMemberAddRequest } from '../models/EntryMemberAddRequest';
import type { EntryMemberRemoveRequest } from '../models/EntryMemberRemoveRequest';
import type { EntrySourceAddRequest } from '../models/EntrySourceAddRequest';
import type { CancelablePromise } from '@/api/core/CancelablePromise';
import { OpenAPI } from '@/api/core/OpenAPI';
import { request as __request } from '@/api/core/request';
export class EntryControllerService {
    /**
     * getEntryList
     * @param collegeId
     * @param createTime
     * @param creatorId
     * @param current
     * @param description
     * @param entryName
     * @param id
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param status
     * @param updateTime
     * @returns BaseResponse_Page_EntryVO_ OK
     * @throws ApiError
     */
    public static getEntryListUsingGet(
        collegeId?: number,
        createTime?: string,
        creatorId?: number,
        current?: number,
        description?: string,
        entryName?: string,
        id?: number,
        pageSize?: number,
        sortField?: string,
        sortOrder?: string,
        status?: number,
        updateTime?: string,
    ): CancelablePromise<BaseResponse_Page_EntryVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/entry',
            query: {
                'collegeId': collegeId,
                'createTime': createTime,
                'creatorId': creatorId,
                'current': current,
                'description': description,
                'entryName': entryName,
                'id': id,
                'pageSize': pageSize,
                'sortField': sortField,
                'sortOrder': sortOrder,
                'status': status,
                'updateTime': updateTime,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * editEntry
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static editEntryUsingPut(
        requestBody?: EntryEditRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/entry',
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
     * addEntry
     * @param requestBody
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addEntryUsingPost1(
        requestBody?: EntryAddRequest,
    ): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/entry',
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
     * getEntryCommentList
     * @param commentType
     * @param content
     * @param createTime
     * @param current
     * @param entryId
     * @param id
     * @param pageSize
     * @param replyId
     * @param sortField
     * @param sortOrder
     * @param updateTime
     * @param userId
     * @returns BaseResponse_Page_EntryCommentVO_ OK
     * @throws ApiError
     */
    public static getEntryCommentListUsingGet(
        commentType?: number,
        content?: string,
        createTime?: string,
        current?: number,
        entryId?: number,
        id?: number,
        pageSize?: number,
        replyId?: number,
        sortField?: string,
        sortOrder?: string,
        updateTime?: string,
        userId?: number,
    ): CancelablePromise<BaseResponse_Page_EntryCommentVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/entry/comment',
            query: {
                'commentType': commentType,
                'content': content,
                'createTime': createTime,
                'current': current,
                'entryId': entryId,
                'id': id,
                'pageSize': pageSize,
                'replyId': replyId,
                'sortField': sortField,
                'sortOrder': sortOrder,
                'updateTime': updateTime,
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
     * addEntryComment
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addEntryCommentUsingPost(
        requestBody?: EntryCommentAddRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/entry/comment',
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
     * removeEntryComment
     * @param commentId commentId
     * @returns BaseResponse_boolean_ OK
     * @throws ApiError
     */
    public static removeEntryCommentUsingDelete(
        commentId: number,
    ): CancelablePromise<BaseResponse_boolean_> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/entry/comment/{commentId}',
            path: {
                'commentId': commentId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
            },
        });
    }
    /**
     * addEntryMember
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addEntryMemberUsingPost(
        requestBody?: EntryMemberAddRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/entry/member',
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
     * removeEntryMember
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @throws ApiError
     */
    public static removeEntryMemberUsingDelete(
        requestBody?: EntryMemberRemoveRequest,
    ): CancelablePromise<BaseResponse_boolean_> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/entry/member',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
            },
        });
    }
    /**
     * addEntrySource
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addEntrySourceUsingPost(
        requestBody?: EntrySourceAddRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/entry/source',
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
     * removeEntrySource
     * @param sourceId sourceId
     * @returns BaseResponse_boolean_ OK
     * @throws ApiError
     */
    public static removeEntrySourceUsingDelete(
        sourceId: number,
    ): CancelablePromise<BaseResponse_boolean_> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/entry/source/{sourceId}',
            path: {
                'sourceId': sourceId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
            },
        });
    }
    /**
     * getEntryById
     * @param entryId entryId
     * @returns BaseResponse_EntryVO_ OK
     * @throws ApiError
     */
    public static getEntryByIdUsingGet(
        entryId: number,
    ): CancelablePromise<BaseResponse_EntryVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/entry/{entryId}',
            path: {
                'entryId': entryId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * removeEntry
     * @param entryId entryId
     * @returns BaseResponse_boolean_ OK
     * @throws ApiError
     */
    public static removeEntryUsingDelete(
        entryId: number,
    ): CancelablePromise<BaseResponse_boolean_> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/entry/{entryId}',
            path: {
                'entryId': entryId,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
            },
        });
    }
}

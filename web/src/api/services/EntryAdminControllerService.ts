/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type {BaseResponse_boolean_} from '../models/BaseResponse_boolean_';
import type {BaseResponse_long_} from '../models/BaseResponse_long_';
import type {EntryAddRequest} from '../models/EntryAddRequest';
import type {EntryUpdateRequest} from '../models/EntryUpdateRequest';
import type {CancelablePromise} from '../core/CancelablePromise';
import {OpenAPI} from '../core/OpenAPI';
import {request as __request} from '../core/request';

export class EntryAdminControllerService {
    /**
     * UpdateEntry
     * @param requestBody
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static updateEntryUsingPut(
        requestBody?: EntryUpdateRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/admin/entry',
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
    public static addEntryUsingPost(
        requestBody?: EntryAddRequest,
    ): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/admin/entry',
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
     * DeleteEntry
     * @param entryId entryId
     * @returns BaseResponse_boolean_ OK
     * @throws ApiError
     */
    public static deleteEntryUsingDelete(
        entryId: number,
    ): CancelablePromise<BaseResponse_boolean_> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/admin/entry/{entryId}',
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

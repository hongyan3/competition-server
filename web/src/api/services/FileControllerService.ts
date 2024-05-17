/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_string_ } from '../models/BaseResponse_string_';
import type { CancelablePromise } from '@/api/core/CancelablePromise';
import { OpenAPI } from '@/api/core/OpenAPI';
import { request as __request } from '@/api/core/request';
export class FileControllerService {
    /**
     * UploadFile
     * @param formData
     * @returns BaseResponse_string_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static uploadFileUsingPost(
        formData?: {
            /**
             * business
             */
            business: string;
            /**
             * file
             */
            file: Blob;
        },
    ): CancelablePromise<BaseResponse_string_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/file/upload',
            formData: formData,
            mediaType: 'multipart/form-data',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
}

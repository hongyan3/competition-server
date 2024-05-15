import {Notification} from "@arco-design/web-vue";

export const message = {
    success: (msg: string) => {
        Notification.success(msg)
    },
    error: (msg: string) => {
        Notification.error(msg)
    },
    warning: (msg: string) => {
        Notification.warning(msg)
    },
    info: (msg: string) => {
        Notification.info(msg)
    }
}
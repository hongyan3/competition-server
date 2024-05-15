import Message from 'tdesign-miniprogram/message/index';

let showMessage = {
    info(message) {
        Message.info({
            offset: [20, 32],
            duration: 2000,
            content: message,
        });
    },
    warning(message) {
        Message.warning({
            offset: [20, 32],
            duration: 2000,
            content: message,
        });
    },
    error(message) {
        Message.error({
            offset: [20, 32],
            duration: 2000,
            content: message,
        });
    },
    success(message) {
        Message.success({
            offset: [20, 32],
            duration: 2000,
            content: message,
        });
    }
}
export default showMessage
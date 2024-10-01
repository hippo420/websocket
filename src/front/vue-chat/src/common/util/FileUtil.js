export default{

    fnGetProfileImageUrl(data){
        var imageData = data
        if(data == null){ return 'default.png'; }
        const byteCharacters = new Uint8Array(imageData);
        let binaryString = '';
        byteCharacters.forEach((byte) => {
            binaryString += String.fromCharCode(byte);
        });

        return `data:;base64,${binaryString}`;
    }
}
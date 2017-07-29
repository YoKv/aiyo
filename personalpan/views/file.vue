<template lang="html">
    <div>
    <form method="post" action="http://upload.qiniu.com/"
     enctype="multipart/form-data">
<!--      <input name="key" type="hidden" v-bind:value="bucketKey" />-->
<!--      <input name="x:<custom_name>" type="hidden" value="<custom_value>">-->
      <input name="token" type="hidden" v-bind:value="uploadToken" />
      <input name="file" type="file" />
      <!--<input name="crc32" type="hidden" />-->
<!--      <input name="accept" type="hidden" />-->
      <input type="submit"  value="确定"/>
    </form>


    </div>
</template>

<script>
export default {
    data: function() {
        return {
        }
    }
}
</script>

<style lang="css">
</style>
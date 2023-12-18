<template>
</template>

<script>
  const baseUrl = process.env.NODE_ENV === 'development'
      ? 'http://localhost:8080' : '';
  export default {
    data() {
      return {
        baseUrl: baseUrl
      }
    }
  }
</script>

<style>
</style>

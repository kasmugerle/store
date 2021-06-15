<template>
    <v-form 
    ref="form" 
    v-model="valid" 
    lazy-validation>
        <v-text-field 
        v-model="name" 
        :rules="nameRules" 
        label="Name" 
        required>
        </v-text-field>
        <v-text-field 
        v-model="username" 
        :rules="usernameRules" 
        label="Username" 
        required>
        </v-text-field>
        <v-text-field 
        v-model="email" 
        :rules="emailRules" 
        label="Email" 
        required>
        </v-text-field>
        <v-text-field 
        v-model="password" 
        :rules="passwordRules" 
        label="Password" 
        required>
        </v-text-field>
        <v-btn
        :disabled="!valid"
        color="success"
        @click="submit"
        >
        Submit
        </v-btn>
        <v-btn
        color="error"
        @click="reset"
        >
        Reset
        </v-btn>
    </v-form>
</template>

<script>
export default {
    data() {
        return {
            name: "",
            username: "",
            email: "",
            password: "", 
            is_admin: null,
            nameRules: [ v => !!v || 'Name is required!' ],
            usernameRules: [ v => !!v || 'Username is required!' ],
            emailRules: [ v => !!v || 'Email is required!' ],
            passwordRules: [ v => !!v || 'Password is required!' ]
        }
    },
    methods: {
        submit: function() {
            this.$refs.form.validate()
            let data = {
                name: this.name,
                username: this.username,
                email: this.email,
                password: this.password,
                is_admin: this.is_admin
            }
            this.$store.dispatch('register', data)
            .then(() => this.$router.push('/login'))
            .catch(e => console.log(e))
        },
        reset() {
            this.$refs.form.reset()
        } 
    }
}
</script>
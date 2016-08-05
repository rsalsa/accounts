import re
from subprocess import Popen, PIPE


# From http://blog.bordage.pro/avoid-docker-py/
class Docker:
    def kill_and_remove(self, ctr_name):
        for action in ('kill', 'rm'):
            command = ['docker', action, ctr_name]
            self.execute(command)

    def get_container_ip(self, ctr_name):
        command = ['docker', 'inspect',
                   '--format', '\'{{.NetworkSettings.IPAddress}}\'',
                   ctr_name]
        return re.sub(r'[^0-9.]*', '', self.execute(command))

    def execute(self, command):
        p = Popen(command, stdout=PIPE, stderr=PIPE)
        out = p.stdout.read()
        stderr = p.stderr.read()
        if p.wait() != 0:
            p.stdout.close()
            p.stderr.close()
            raise RuntimeError("\nstdout:\n" + str(out, 'utf-8') + "\nstderr:\n" + str(stderr, 'utf-8') + "\n")
        p.stdout.close()
        p.stderr.close()
        return str(out, 'utf-8')
